--All the cities in a country organised by largest population to smallest
SELECT city.name, country.name, city.district, city.population 
FROM city JOIN country ON city.countrycode=country.code
WHERE country = 'France'
ORDER BY population DESC
;