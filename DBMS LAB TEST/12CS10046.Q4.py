import re
import sys
import time
import random
import MySQLdb as mdb
import networkx as nx
from networkx.algorithms import *
import matplotlib.pyplot as plt
import pickle

if __name__ == '__main__':
	
	try:
		con = mdb.connect('10.5.18.68', '12CS10046', 'btech12', '12CS10046')
		with con:
			cur = con.cursor()
			cur.execute("drop view if exists AA;")
			cur.execute("create view AA as(select S.AuthorName as author1,T.AuthorName as author2, count(*) as count from pubaut as S, pubaut as T where S.AuthorName != T.AuthorName and strcmp(S.AuthorName,T.AuthorName) = 1 and S.PubID = T.PubID group by author1,author2);");
			con.commit()

			G = nx.Graph()
			cur.execute("select * from AA;")
			con.commit()
			rows = cur.fetchall()
			edge_list = []
			for row in rows:
				e = (row[0],row[1])
				G.add_edge(*e) # unpack edge tuple*

			nx.draw(G)
			plt.show()
			# print nx.min_weighted_vertex_cover(G)

	except mdb.Error, e:
	    if con:
	        con.rollback()		#
	        
	    print "Error %d: %s" % (e.args[0],e.args[1])
	    sys.exit(1)
    
	finally:
	    if con:    
	        con.close()
