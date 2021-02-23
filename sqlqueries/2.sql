--All the countries in a continent organised by largest population to smallest.
SELECT Code, Name, Continent, Region, Population, Capital FROM country
WHERE Continent = 'Europe'
ORDER BY Population DESC