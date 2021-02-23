--The top N populated countries in the world where N is provided by the user.
SELECT code, name, continent, region, population, capital
FROM country
ORDER BY population DESC
LIMIT n 
;