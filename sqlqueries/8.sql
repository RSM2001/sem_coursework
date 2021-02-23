--All the cities in a continent organised by largest population to smallest.
    SELECT city.Name, Country.Name, city.District, city.Population FROM city JOIN country ON city.countrycode=country.code
        WHERE Continent = 'Europe'
    ORDER BY Population DESC

