--this is a comment
SELECT Code, Name, Continent, Region, Population, Capital 
FROM country
WHERE Continent = 'Europe'
ORDER BY Population DESC
LIMIT n 
;