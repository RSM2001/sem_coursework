--All the countries in a region organised by largest population to smallest.
SELECT Code, Name, Continent, Region, Population, Capital
FROM country
WHERE Region = 'Western Europe'
ORDER BY Population DESC
;