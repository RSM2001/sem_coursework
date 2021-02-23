--this is a comment
SELECT Code, Name, Continent, Region, Population, Capital 
FROM country
WHERE Region = 'Eastern Europe'
ORDER BY Population DESC
LIMIT n 
;