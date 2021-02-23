--All the countries in a continent organised by largest population to smallest.
SELECT code, name, continent, region, population, capital FROM country
WHERE continent = 'Europe'
ORDER BY population DESC
;