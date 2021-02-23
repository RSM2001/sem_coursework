--All the countries in a region organised by largest population to smallest.
SELECT code, name, continent, region, population, capital
FROM country
WHERE region = 'Western Europe'
ORDER BY population DESC
;