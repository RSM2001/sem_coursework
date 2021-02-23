--The top number of populated countries in a region where I choose the number.
SELECT code, name, continent, region, population, capital
FROM country
WHERE region = 'Eastern Europe'
ORDER BY population DESC
LIMIT n
;