--The top number of populated countries in a continent where I choose the number.
SELECT code, name, continent, region, population, capital
FROM country
WHERE continent = 'Europe'
ORDER BY population DESC
LIMIT n  
;