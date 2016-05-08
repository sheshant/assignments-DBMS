import re
import sys
import time
import random
import MySQLdb as mdb
import networkx as nx
import matplotlib.pyplot as plt
import pickle

movie_name = {}
actor_name = {}
movie_actor = {}
main_list = []
movie_list = []
actor_list = []
actor_list_name = []
movie_graph = []
movie_index = {}


if __name__ == '__main__':
	start_time = time.time()
	try:
		
		con = mdb.connect('10.5.18.68', '12CS10046', 'btech12', '12CS10046')
		with con:
			cur = con.cursor()
			cur.execute("drop view if exists movie250 ;")
			cur.execute("drop view if exists movie_list ;")
			cur.execute("drop view if exists actor_list ;")
			cur.execute("drop view if exists vcast250 ;")
			con.commit()
			cur.execute("create view movie250 as (select * from 12CS10046_Movie order by rating desc limit 250);")
			cur.execute("create view movie_list as (select MID,title from movie250);")
			cur.execute("create view vcast250 as (select * from 12CS10046_M_Cast where MID in (select MID from movie_list));")
			cur.execute("select * from movie_list;")
			con.commit()
			rows = cur.fetchall()
			for row in rows:
				movie_name[row[0]] = row[1]
				movie_list.append(row[0])

			cur.execute("create view actor_list as (select distinct PID from vcast250);")
			cur.execute("select PID,Name from 12CS10046_Person where PID in(select PID from actor_list) order by name;")
			con.commit()
			rows = cur.fetchall()
			for row in rows:
				actor_name[row[0]] = row[1]
				actor_list.append(row[0])

			G = nx.Graph()
			Gact = nx.Graph()
			G.add_nodes_from(movie_list)
			Gact.add_nodes_from(actor_list)
			for movie in movie_list:
				cur.execute("drop view if exists view1")
				con.commit()
				cur.execute("create view view1 as (select distinct PID from vcast250 where MID = '" + movie + "'); ")
				con.commit()
				cur.execute("select MID from vcast250 where PID in (select PID from view1) and MID != '" + movie + "';")
				con.commit()
				rows = cur.fetchall()
				l = []
				for row in rows:
					t = (movie,row[0])
					l.append(t)
				G.add_edges_from(l)
				cur.execute("drop view if exists view1")
				con.commit()
				del l

			print nx.graph_clique_number(G);
			for actor in actor_list:
				cur.execute("drop view if exists view2;")
				con.commit()
				cur.execute("create view view2 as (select distinct MID from vcast250 where PID = '" + actor + "'); ")
				con.commit()
				cur.execute("select distinct PID from vcast250 where MID in (select MID from view2) and PID != '" + actor + "';")
				con.commit()
				rows = cur.fetchall()
				l = []
				for row in rows:
					t = (actor,row[0])
					l.append(t)
				Gact.add_edges_from(l)
				cur.execute("drop view if exists view2;")
				con.commit()
				del l
			pickle.dump(Gact, open('Graph_actor.txt', 'w'))
			dic = nx.all_pairs_shortest_path_length(Gact)
			with open('filename.pickle', 'wb') as handle:
  				pickle.dump(dic, handle)
  			print "done"
			cur.execute("drop table if exists Separation;")
			con.commit()
			cur.execute("create table Separation (actor1 VARCHAR(100),actor2 VARCHAR(100),separation INTEGER);")
			con.commit()
			s = ""
			st = '"'
			i = 0
			file_write = open("mysql.txt",'w')
			for actor in actor_list:
				m = dic[actor]
				for actor2 in actor_list:
					if actor2 in m:
						s = str('{:<30} {:<30} {:<10} \n'.format(actor_name[actor],actor_name[actor2],"YES"))
						file_write.write(s)
						cur.execute("insert into Separation values ( "+st+" " +actor_name[actor]+ " "+st+" ,  "+st+" " +actor_name[actor2]+ " "+st+" , " +str(m[actor2])+ ");")
						con.commit()
					else:
						s = str('{:<30} {:<30} {:<10} \n'.format(actor_name[actor],actor_name[actor2],"NO"))
						file_write.write(s)


			file_write.close()
			cur.execute("drop view if exists movie250 ;")
			cur.execute("drop view if exists movie_list ;")
			cur.execute("drop view if exists actor_list ;")
			cur.execute("drop view if exists vcast250 ;")
			con.commit()

		print "Time taken"
		print time.time() - start_time
	except mdb.Error, e:
	    if con:
	        con.rollback()		#
	        
	    print "Error %d: %s" % (e.args[0],e.args[1])
	    sys.exit(1)
    
	finally:
	    if con:    
	        con.close()
