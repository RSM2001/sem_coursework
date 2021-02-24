--All the cities in a district organised by largest population to smallest
SELECT city.name, country.name, city.district, city.population 
FROM city JOIN country ON city.countrycode=country.code
WHERE district = 'Bretagne'
ORDER BY population DESC
;