--All the cities in the world organised by largest population to smallest.
    SELECT city.Name, Country.Name, city.District, city.Population FROM city JOIN country ON city.countryCode = country.code
    ORDER BY Population DESC
