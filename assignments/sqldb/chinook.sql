--1a: Write a Query that returns the following data from the Customers table
SELECT
  cu.firstname||" "||cu.lastname AS fullname,
  cu.company,
  cu.city,
  cu.state
FROM main.customers cu
ORDER BY cu.city;

--1b: Modify your query to only return customers from Canada or the United States
SELECT
  cu.firstname||" "||cu.lastname AS fullname,
  cu.company,
  cu.city,
  cu.state
FROM main.customers cu
WHERE cu.country IN ("Canada", "USA")
ORDER BY cu.city;

--1c:  Modify your query to only return customers in Canada or the United States whose last name starts with the letter M.
SELECT
  cu.firstname||" "||cu.lastname AS fullname,
  cu.company,
  cu.city,
  cu.state
FROM main.customers cu
WHERE cu.country IN ("Canada", "USA")
  AND cu.lastname LIKE "M%"
ORDER BY cu.city;

--2a: Write a query white returns the following information from the Artist, Albums and Tracks tables:
SELECT
  ar.name AS artist,
  al.title AS album,
  tr.name AS track
FROM
  main.artists ar,
  main.albums al,
  main.tracks tr
WHERE ar.artistid = al.artistid
  AND al.albumid = tr.albumid
ORDER BY ar.name;

--2b: Modify the previous query so it only returns tracks that have the word “dancing” somewhere in the track name.
SELECT
  ar.name AS artist,
  al.title AS album,
  tr.name AS track
FROM 
  main.artists ar,
  main.albums al,
  main.tracks tr
WHERE ar.artistid = al.artistid
  AND al.albumid = tr.albumid
  AND tr.name LIKE "%dancing%"
ORDER BY ar.name;

--2c: You have been asked to create a org chart for the company. Create a query that returns two columns one with the employee’s 
SELECT
  em.firstname||" "||em.lastname AS employee,
  mr.firstname||" "||mr.lastname AS manager
FROM main.employees em
  INNER JOIN main.employees mr ON em.reportsto = mr.employeeid;

--2d: When you review the list you notice that the General Manager, Andrew Adams, is not included on the list.  Modify your query 
SELECT
  em.firstname||" "||em.lastname AS employee,
  IFNULL(mr.firstname||" "||mr.lastname, "reports to himself") AS manager
FROM main.employees em
  LEFT OUTER JOIN main.employees mr ON em.reportsto = mr.employeeid;

--3a: Create a report that that Joins the Albums to Tracks table and returns the title of each album and the number of tracks it contains.
SELECT
  al.title,
  COUNT(tr.trackid) AS "numoftracks"
FROM
  main.albums al,
  main.tracks tr
WHERE al.albumid = tr.albumid
GROUP BY al.title;

--3b: Modify your query from 3a so that the report only shows albums that have more than ten tracks.
SELECT
  al.title,
  COUNT(tr.trackid) AS "numoftracks"
FROM
  main.albums al,
  main.tracks tr
WHERE al.albumid = tr.albumid
GROUP BY al.title
HAVING COUNT(tr.trackid) > 10;
