--The population of people, people living in cities, and people not living in cities in each region.
SELECT DISTINCT country.region, SUM(DISTINCT country.population),  CONCAT(ROUND(SUM(DISTINCT city.population)/SUM(DISTINCT country.population)*100), '%'), CONCAT(ROUND((SUM(DISTINCT country.population)-SUM(DISTINCT city.population))/SUM(DISTINCT country.population)*100), '%')
FROM city JOIN country ON city.countrycode=country.code
GROUP BY region
;