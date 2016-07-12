import sys 
import requests 
import lxml.html 
import re
import MySQLdb as mdb 

# to find the gender 
def getGender(s):
	st = ""
	gen = ""
	xs = lxml.html.document_fromstring(requests.get("http://www.imdb.com" + s).content)
	q = xs.xpath('//*[@id="name-job-categories"]/a/span/text()')
	q = map(lambda s: s.strip(), q) # in case if it is actor or actress 
	if "Actor" in q:
		gen = "male"
	elif "Actress" in q:
		gen = "female"
	else: 			# in case if not found there then we are examing full bio 
		t = xs.xpath('//*[@id="name-bio-text"]/div/div/text()')
		for d in t:
			st += d
		if(st.count("He") > st.count("She")):
			gen = "male"
		elif(st.count("He") < st.count("She")):
			gen = "female"
		else:
			gen = "not found"   #in case even if not found there then we have no other option
	return gen

def getString(s):   		# function to get the day date in format

	stt = ""
	if(len(s) > 1):
		st = s[0] + " " +s[1]
		month = {}
		month["January"] 	= "01"
		month["February"] 	= "02"
		month["March"] 		= "03"
		month["April"] 		= "04"
		month["May"] 		= "05"
		month["June"] 		= "06"
		month["July"] 		= "07"
		month["August"] 	= "08"
		month["September"] 	= "09"
		month["October"] 	= "10"
		month["November"] 	= "11"
		month["December"] 	= "12"

		stree = re.findall('\d+', st)
		ss = re.findall("[a-zA-Z]+", st)
		if len(stree[0]) == 1:
			stree[0] = "0" + stree[0]
		stt = stree[1] + "-" + month[ss[0]] + "-" + stree[0]
	return stt

def getMovie(id): 
	hxs = lxml.html.document_fromstring(requests.get("http://www.imdb.com/title/" + id).content)
	movie = {}
	list_dir = []
	list_cast = []
	try:
		movie['title'] = hxs.xpath('//*[@id="overview-top"]/h1/span[1]/text()')[0].strip()
	except IndexError:
		movie['title']
	try:
		movie['year'] = hxs.xpath('//*[@id="overview-top"]/h1/span[2]/a/text()')[0].strip()
	except IndexError:
		try:
			movie['year'] = hxs.xpath('//*[@id="overview-top"]/h1/span[3]/a/text()')[0].strip()
		except IndexError:
			movie['year'] = ""
	try:
		movie['genre'] = hxs.xpath('//*[@id="overview-top"]/div[2]/a/span/text()')
	except IndexError:
		movie['genre'] = []
	
	try:
		movie['rating'] = hxs.xpath('//*[@id="overview-top"]/div[3]/div[3]/strong/span/text()')[0]
	except IndexError:
		movie['rating'] = ""
	
	try:
		movie['director'] = hxs.xpath('//*[@id="overview-top"]/div[4]/a/span/text()')
	except IndexError:
		movie['director'] = ""
	try:
		movie['stars'] = hxs.xpath('//*[@id="overview-top"]/div[6]/a/span/text()')
	except IndexError:
		movie['stars'] = ""
	
	try:
		movie['votes'] = hxs.xpath('//*[@id="overview-top"]/div[3]/div[3]/a[1]/span/text()')[0].strip()
		s = movie['votes'].replace(",","")
		movie['votes'] = s
	except IndexError:
		movie['votes'] = ""
	try:
		movie["director-ref"] = hxs.xpath('//*[@id="overview-top"]/div[4]/a/@href')
	except IndexError:
		movie['director-ref'] = ""
	try:
		movie["cast-ref"] = hxs.xpath('//*[@id="overview-top"]/div[6]/a/@href')
	except IndexError:
		movie['cast-ref'] = ""
	try:
		movie["country"] = hxs.xpath('//*[@id="titleDetails"]/div/h4[text()="Country:"]/following-sibling::a/text()')
	except IndexError:
		movie["country"] = ""
	try:
		movie["location"] = hxs.xpath('//*[@id="titleDetails"]/div/h4[text()="Filming Locations:"]/following-sibling::a/text()')
	except IndexError:
		movie['location'] = ""
	try:
		movie["language"] = hxs.xpath('//*[@id="titleDetails"]/div/h4[text()="Language:"]/following-sibling::a/text()')
	except IndexError:
		movie["language"] = ""

	ls = []
	for s in movie["director-ref"]:
		xs = lxml.html.document_fromstring(requests.get("http://www.imdb.com" + s).content)
		try:
			ls.append(xs.xpath('//*[@id="name-born-info"]/h4[text()="Born:"]/following-sibling::time/a/text()'))
		except IndexError:
			print "error"

	lss = []
	for s in ls:
		lss.append(getString(s))
	movie["director-DOB"] = lss
	del ls
	del lss

	list_cast = []
	for s in movie["cast-ref"]:
		xs = lxml.html.document_fromstring(requests.get("http://www.imdb.com" + s).content)
		try:
			list_cast.append(xs.xpath('//*[@id="name-born-info"]/h4[text()="Born:"]/following-sibling::time/a/text()'))
		except IndexError:
			list_cast.append("")

	ls = [] 
	lst = []
	for s in list_cast:
		ls.append(getString(s))
	movie["cast-DOB"] = ls
	del list_cast
	stre = "fullcredits?ref_=tt_cl_sm#cast"

	xs = lxml.html.document_fromstring(requests.get("http://www.imdb.com/title/" + id + stre).content)
	try:
		t = xs.xpath('//*[@id="fullcredits_content"]/table[4]/tbody/tr/td/a/text()')
		movie["producer"] = map(lambda s: s.strip(), t)
	except IndexError:
		movie["producer"] = ""
	try:
		movie["producer-ref"] = xs.xpath('//*[@id="fullcredits_content"]/table[4]/tbody/tr/td/a/@href')
		
	except IndexError:
		movie["producer-ref"] = ""

	l = []
	ls = []
	for s in movie["producer-ref"]:
		xxs = lxml.html.document_fromstring(requests.get("http://www.imdb.com/title/" + s).content)
		try:
			l.append(xxs.xpath('//*[@id="name-born-info"]/h4[text()="Born:"]/following-sibling::time/a/text()'))
		except IndexError:
			l.append("")

	for s in l:
		ls.append(getString(s))
	movie["producer-DOB"] = ls
	del l 
	del ls

	l = []
	for s in movie["cast-ref"]:
		l.append(getGender(s))
	movie["cast-gen"] = l
	del l
	l = []
	for s in movie["producer-ref"]:
		l.append(getGender(s))
	movie["producer-gen"] = l
	del l
	l = []
	for s in movie["director-ref"]:
		l.append(getGender(s))
	movie["director-gen"] = l
	del l
	return movie


if __name__ == "__main__":
	try:
		file_read = open("12CS10046.txt", 'r')
		s = file_read.readlines()
		prefix =  s[1].rstrip('\n')
		stri   =  s[0].rstrip('\n')
	except:
		print "error"
	finally:
		file_read.close()
	try:
		con = mdb.connect('10.5.18.68', '12CS10046', 'btech12', '12CS10046')
		cur = con.cursor()
		cur.execute("drop table if exists "+prefix+"M_Location")
		cur.execute("drop table if exists "+prefix+"M_Country")
		cur.execute("drop table if exists "+prefix+"M_Language")
		cur.execute("drop table if exists "+prefix+"M_Genre")
		cur.execute("drop table if exists "+prefix+"M_Cast")
		cur.execute("drop table if exists "+prefix+"M_Director")
		cur.execute("drop table if exists "+prefix+"M_Producer")
		cur.execute("drop table if exists "+prefix+"Location")
		cur.execute("drop table if exists "+prefix+"Country")
		cur.execute("drop table if exists "+prefix+"Language")
		cur.execute("drop table if exists "+prefix+"Genre")
		cur.execute("drop table if exists "+prefix+"Person")
		cur.execute("drop table if exists "+prefix+"Movies")
		cur.execute("create table "+prefix+"Movies (MID VARCHAR(10),title VARCHAR(100),year INTEGER,rating FLOAT,num_votes INTEGER, CONSTRAINT mov_pk PRIMARY KEY(MID))")
		cur.execute("create table "+prefix+"Person(PID VARCHAR(10),Name VARCHAR(700),DOB DATE, Gender VARCHAR(10), CONSTRAINT pe_pk PRIMARY KEY(PID))")
		cur.execute("create table "+prefix+"Genre(GID VARCHAR(10),Name VARCHAR(30), CONSTRAINT pe_pk PRIMARY KEY(GID))")
		cur.execute("create table "+prefix+"Language(LAID VARCHAR(10),Name VARCHAR(30), CONSTRAINT pe_pk PRIMARY KEY(LAID))")
		cur.execute("create table "+prefix+"Country(CID VARCHAR(10),Name VARCHAR(30), CONSTRAINT pe_pk PRIMARY KEY(CID))")
		cur.execute("create table "+prefix+"Location(LID VARCHAR(10),Name VARCHAR(100), CONSTRAINT pe_pk PRIMARY KEY(LID))")
		cur.execute("create table "+prefix+"M_Producer(ID VARCHAR(10),MID VARCHAR(10),PID VARCHAR(10), CONSTRAINT pr_pk PRIMARY KEY(ID), CONSTRAINT p1_fk FOREIGN KEY(MID) REFERENCES "+prefix+"Movies(MID), CONSTRAINT p2_fk FOREIGN KEY(PID) REFERENCES "+prefix+"Person(PID))")
		cur.execute("create table "+prefix+"M_Director(ID VARCHAR(10),MID VARCHAR(10),PID VARCHAR(10), CONSTRAINT di_pk PRIMARY KEY(ID), CONSTRAINT d1_fk FOREIGN KEY(MID) REFERENCES "+prefix+"Movies(MID), CONSTRAINT d2_fk FOREIGN KEY(PID) REFERENCES "+prefix+"Person(PID))")
		cur.execute("create table "+prefix+"M_Cast(ID VARCHAR(10),MID VARCHAR(10),PID VARCHAR(10), CONSTRAINT ca_pk PRIMARY KEY(ID), CONSTRAINT c1_fk FOREIGN KEY(MID) REFERENCES "+prefix+"Movies(MID), CONSTRAINT c2_fk FOREIGN KEY(PID) REFERENCES "+prefix+"Person(PID))")
		cur.execute("create table "+prefix+"M_Genre(ID VARCHAR(10),MID VARCHAR(10),GID VARCHAR(10), CONSTRAINT ge_pk PRIMARY KEY(ID), CONSTRAINT g1_fk FOREIGN KEY(MID) REFERENCES "+prefix+"Movies(MID), CONSTRAINT g2_fk FOREIGN KEY(GID) REFERENCES "+prefix+"Genre(GID))")
		cur.execute("create table "+prefix+"M_Language(ID VARCHAR(10),MID VARCHAR(10),LAID VARCHAR(10), CONSTRAINT la_pk PRIMARY KEY(ID), CONSTRAINT la1_fk FOREIGN KEY(MID) REFERENCES "+prefix+"Movies(MID), CONSTRAINT la2_fk FOREIGN KEY(LAID) REFERENCES "+prefix+"Language(LAID))")
		cur.execute("create table "+prefix+"M_Country(ID VARCHAR(10),MID VARCHAR(10),CID VARCHAR(10), CONSTRAINT co_pk PRIMARY KEY(ID), CONSTRAINT co1_fk FOREIGN KEY(MID) REFERENCES "+prefix+"Movies(MID), CONSTRAINT co2_fk FOREIGN KEY(CID) REFERENCES "+prefix+"Country(CID))")
		cur.execute("create table "+prefix+"M_Location(ID VARCHAR(10),MID VARCHAR(10),LID VARCHAR(10), CONSTRAINT lo_pk PRIMARY KEY(ID), CONSTRAINT lo1_fk FOREIGN KEY(MID) REFERENCES "+prefix+"Movies(MID), CONSTRAINT lo2_fk FOREIGN KEY(LID) REFERENCES "+prefix+"Location(LID))")
		main_list = []
		title_list = []
		main_list.append(stri)
		for i in range(4):
			hxs = lxml.html.document_fromstring(requests.get(stri).content)
			try:
				strlist = hxs.xpath('//*[@id="right"]/span/a/@href')
				stri = "http://www.imdb.com" + strlist[len(strlist)-1]
				main_list.append(stri)

			except IndexError:
				stri = ""

		movie_list = []
		term = "href="
		title = "title/tt"
		for string in main_list:
			s =  requests.get(string).content
			lis = s.split(" ")
			del s
			for s in lis:
				if(term in s and title in s and len(s) == 24):
					title_list.append(s[13:22])

			del lis
		del main_list

		# 
		mid = 1000
		pid = 1000
		gid = 1000
		lid = 1000
		laid = 1000
		cid = 1000
		m_pid = 1000
		m_coid = 1000
		m_caid = 1000
		m_gid = 1000
		m_laid = 1000
		m_lid = 1000
		m_did = 1000
		# title_list = ["tt0050870","tt0367110"]
		for s in title_list:
			movie = getMovie(s)
			Mi = "M" + str(mid)
			Pi = "P" + str(pid)
			Gi = "G" + str(gid)
			Li = "L" + str(lid)
			LAi = "LA" + str(laid)
			Ci = "C" + str(cid)
			mpi = "MP" + str(m_pid)
			mcoi = "MCO" + str(m_coid)
			mcai = "MCA" + str(m_caid)
			mgi = "MG" + str(m_gid)
			mlai = "MLA" + str(m_laid)
			mdi = "MD" + str(m_did)
			mli = "ML" + str(m_lid)
			st = "insert into "+prefix+"Movies values ('"+ Mi+"','"+movie['title']+"',"+movie['year']+","+movie['rating']+","+movie['votes']+");"
			con.commit()
			cur.execute(st)
			mid += 1

			for gen in movie['genre']:
				rows = cur.execute("insert into "+prefix+"Genre (GID,Name) SELECT * FROM (SELECT '"+Gi+"' , '"+gen+"') AS tmp WHERE NOT EXISTS (SELECT Name FROM "+prefix+"Genre WHERE Name = '"+gen+"');")
				con.commit()
				if(rows >= 1):
					cur.execute("insert into "+prefix+"M_Genre values('"+mgi+"', '"+Mi+"', '"+Gi+"');")
					m_gid += 1
					gid += 1
					mgi = "MG" + str(m_gid)
					Gi = "G" + str(gid)
				else:
					cur.execute("select GID from "+prefix+"Genre where Name = '"+gen+"' ;")
					r = cur.fetchone()
					rstring = r[0]
					cur.execute("insert into "+prefix+"M_Genre values('"+mgi+"', '"+Mi+"', '"+rstring+"');")
					m_gid += 1
					mgi = "MG" + str(m_gid)

			for lang in movie["language"]:
				rows = cur.execute("insert into "+prefix+"Language (LAID,Name) SELECT * FROM (SELECT '"+LAi+"' , '"+lang+"') AS tmp WHERE NOT EXISTS (SELECT Name FROM "+prefix+"Language WHERE Name = '"+lang+"');")
				con.commit()
				if(rows >= 1):
					cur.execute("insert into "+prefix+"M_Language values('"+mlai+"', '"+Mi+"', '"+LAi+"');")
					m_laid += 1
					laid += 1
					mlai = "MLA" + str(m_laid)
					LAi = "LA" + str(laid)
				else:
					cur.execute("select LAID from "+prefix+"Language where Name = '"+lang+"' ;")
					r = cur.fetchone()
					rstring = r[0]
					cur.execute("insert into "+prefix+"M_Language values('"+mlai+"', '"+Mi+"', '"+rstring+"');")
					m_laid += 1
					mlai = "MLA" + str(m_laid)

			for loc in movie["location"]:
				rows = cur.execute("insert into "+prefix+"Location (LID,Name) SELECT * FROM (SELECT '"+Li+"' , '"+loc+"') AS tmp WHERE NOT EXISTS (SELECT Name FROM "+prefix+"Location WHERE Name = '"+loc+"');")
				con.commit()
				if(rows >= 1):
					cur.execute("insert into "+prefix+"M_Location values('"+mli+"', '"+Mi+"', '"+Li+"');")
					m_lid += 1
					lid += 1
					mli = "ML" + str(m_lid)
					Li = "L" + str(lid)
				else:
					cur.execute("select LID from "+prefix+"Location where Name = '"+loc+"' ;")
					r = cur.fetchone()
					rstring = r[0]
					cur.execute("insert into "+prefix+"M_Location values('"+mli+"', '"+Mi+"', '"+rstring+"');")
					m_lid += 1
					mli = "ML" + str(m_lid)

			for coun in movie["country"]:
				rows = cur.execute("insert into "+prefix+"Country (CID,Name) SELECT * FROM (SELECT '"+Ci+"' , '"+coun+"') AS tmp WHERE NOT EXISTS (SELECT Name FROM "+prefix+"Country WHERE Name = '"+coun+"');")
				con.commit()
				if(rows >= 1):
					cur.execute("insert into "+prefix+"M_Country values('"+mcoi+"', '"+Mi+"', '"+Ci+"');")
					m_coid += 1
					cid += 1
					mcoi = "MCO" + str(m_coid)
					Ci = "C" + str(cid)
				else:
					cur.execute("select CID from "+prefix+"Country where Name = '"+coun+"' ;")
					r = cur.fetchone()
					rstring = r[0]
					cur.execute("insert into "+prefix+"M_Country values('"+mcoi+"', '"+Mi+"', '"+rstring+"');")
					m_coid += 1
					mcoi = "MCO" + str(m_coid)

			for i in range(len(movie['stars'])):
				scast = movie['stars'][i]
				sdob = movie["cast-DOB"][i]
				gender = movie["cast-gen"][i]
				if (sdob == ""):
					sdob = "null"
				else:
					sdob = "'" + sdob + "'"
				rows = cur.execute("insert into "+prefix+"Person (PID,Name,DOB,Gender) SELECT * FROM (SELECT '"+Pi+"' , '"+scast+"', "+sdob+",'"+gender+"') AS tmp WHERE NOT EXISTS (SELECT Name FROM "+prefix+"Person WHERE Name = '"+scast+"');")
				con.commit()
				if(rows >= 1):
					cur.execute("insert into "+prefix+"M_Cast values('"+mcai+"', '"+Mi+"', '"+Pi+"');")
					m_caid += 1
					pid += 1
					mcai = "MCA" + str(m_caid)
					Pi = "P" + str(pid)
				else:
					cur.execute("select PID from "+prefix+"Person where Name = '"+scast+"' ;")
					r = cur.fetchone()
					rstring = r[0]
					cur.execute("insert into "+prefix+"M_Cast values('"+mcai+"', '"+Mi+"', '"+rstring+"');")
					m_caid += 1
					mcai = "MCA" + str(m_caid)
			for i in range(len(movie["producer"])):
				spro = movie["producer"][i]
				sdob = movie["producer-DOB"][i]
				gender = movie["producer-gen"][i]
				if (sdob == ""):
					sdob = "null"
				else:
					sdob = "'" + sdob + "'"
				rows = cur.execute("insert into "+prefix+"Person (PID,Name,DOB,Gender) SELECT * FROM (SELECT '"+Pi+"' , '"+spro+"', "+sdob+",'"+gender+"') AS tmp WHERE NOT EXISTS (SELECT Name FROM "+prefix+"Person WHERE Name = '"+spro+"');")

				con.commit()
				if(rows >= 1):
					cur.execute("insert into "+prefix+"M_Producer values('"+mpi+"', '"+Mi+"', '"+Pi+"');")
					m_pid += 1
					pid += 1
					mpi = "MP" + str(m_pid)
					Pi = "P" + str(pid)
				else:
					cur.execute("select PID from "+prefix+"Person where Name = '"+spro+"' ;")
					r = cur.fetchone()
					rstring = r[0]
					cur.execute("insert into "+prefix+"M_Producer values('"+mpi+"', '"+Mi+"', '"+rstring+"');")
					m_pid += 1
					mpi = "MP" + str(m_pid)

			for i in range(len(movie['director'])):
				sdir = movie['director'][i]
				sdob = movie["director-DOB"][i]
				gender = movie["director-gen"][i]
				if (sdob == ""):
					sdob = "null"
				else:
					sdob = "'" + sdob + "'"
				rows = cur.execute("insert into "+prefix+"Person (PID,Name,DOB,Gender) SELECT * FROM (SELECT '"+Pi+"' , '"+sdir+"', "+sdob+",'"+gender+"') AS tmp WHERE NOT EXISTS (SELECT Name FROM "+prefix+"Person WHERE Name = '"+sdir+"');")
				con.commit()
				if(rows >= 1):
					cur.execute("insert into "+prefix+"M_Director values('"+mdi+"', '"+Mi+"', '"+Pi+"');")
					m_did += 1
					pid += 1
					mdi = "MD" + str(m_did)
					Pi = "P" + str(pid)
				else:
					cur.execute("select PID from "+prefix+"Person where Name = '"+sdir+"' ;")
					r = cur.fetchone()
					rstring = r[0]
					cur.execute("insert into "+prefix+"M_Director values('"+mdi+"', '"+Mi+"', '"+rstring+"');")
					m_did += 1
					mdi = "MD" + str(m_did)


			del movie
		con.commit()

	except mdb.Error, e:
		print "Error %d: %s" % (e.args[0],e.args[1])
		sys.exit(1)

	finally:
		if con:
			con.close()