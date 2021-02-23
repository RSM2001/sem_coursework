--The top N populated countries in the world where N is provided by the user.
SELECT Code, Name, Continent, Region, Population, Capital
FROM country
ORDER BY Population DESC
LIMIT n
;