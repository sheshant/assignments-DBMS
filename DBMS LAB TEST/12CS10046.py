import string
import sys 
import MySQLdb as mdb
import networkx as nx

if __name__ == '__main__':
	try:
		con = mdb.connect('10.5.18.68','12CS10046','btech12','12CS10046')
		cur = con.cursor()
		with con:
			pub = 0
			aut = 0
			author_list = [] #this will store the list of all authors 
			author_map = {}
			cur.execute("DROP TABLE if exists Publish")
			cur.execute("drop table if exists Authors")
			cur.execute("drop table if exists Publications")
			cur.execute("CREATE TABLE Publications (PubID VARCHAR(30), title VARCHAR(300), year INTEGER ,CONSTRAINT PRIMARY KEY pk (PubID));")
			cur.execute("CREATE TABLE Authors( AuthorID VARCHAR(30), AuthorName VARCHAR(30),CONSTRAINT PRIMARY KEY pk2 (AuthorID));")
			cur.execute("CREATE TABLE Publish( PubID VARCHAR(30), AuthorID VARCHAR(30),CONSTRAINT FOREIGN KEY fk1(PubID)  REFERENCES Publications(PubID),CONSTRAINT FOREIGN KEY fk2(AuthorID) REFERENCES Authors(AuthorID));")
			con.commit()
			csv_file = open("Data%20File.txt")
			string_file = csv_file.read()
			lines = string_file.split("\n")
			s = '"'
			for line in lines:
				data = line.split(',')
				author = data[-1].split('|')
				del author[-1]
				print author
				print "insert into Publications VALUES ('"+data[0]+"',"+s+data[1]+s+","+data[2]+");"
				cur.execute("insert into Publications VALUES ('"+data[0]+"',"+s+data[1]+s+","+data[2]+");")
				
				# good we inserted th epublication values now we need to insert into author first 
				# we will go through the author data 

				for au in author:
					# case 1 if that author is already in the list
					if au in author_list:
						# then we need to find the key and insert it to the Publish
						cur.execute("insert into Publish VALUES ('"+data[0]+"','"+str(author_map[au])+"')")

					else:
						# case 2 if that author is not in the list
						# we need to add it in the list with some key
						aut = aut+1
						author_list.append(au)
						author_map[au] = aut
						cur.execute("insert into Authors VALUES ('"+str(author_map[au])+"','"+au+"')")
						cur.execute("insert into Publish VALUES ('"+data[0]+"','"+str(author_map[au])+"')")

			con.commit()


			csv_file.close()


	except mdb.Error, e:
		print "Error %d: %s" % (e.args[0],e.args[1])
		sys.exit(1)

	finally:
		if(con):
			con.close()

