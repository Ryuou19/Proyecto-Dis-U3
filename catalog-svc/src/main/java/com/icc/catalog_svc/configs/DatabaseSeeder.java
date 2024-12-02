package com.icc.catalog_svc.configs;

import com.icc.catalog_svc.models.Store;
import com.icc.catalog_svc.models.Product;
import com.icc.catalog_svc.repositories.StoreRepository;
import com.icc.catalog_svc.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner seedDatabase(StoreRepository storeRepository, ProductRepository productRepository) {
        return args -> {
            if (storeRepository.count() == 0 && productRepository.count() == 0) {
                // Crear tiendas con datos reales
                Store store1 = createStore("Pasta Palace", "Italian Food", "Santiago", 4.5, "pasta_palace_logo.png");
                Store store2 = createStore("Burger Haven", "Fast Food", "Curico", 4.2, "burger_haven_logo.png");
                Store store3 = createStore("Sushi World", "Japanese Food", "Curico", 4.8, "sushi_world_logo.png");
                Store store4 = createStore("Taco Town", "Mexican Food", "Concepción", 4.3, "taco_town_logo.png");
                Store store5 = createStore("Pizza Planet", "Pizza", "La Serena", 4.6, "pizza_planet_logo.png");

                List<Store> stores = Arrays.asList(store1, store2, store3, store4, store5);
                storeRepository.saveAll(stores);

                // Crear productos para cada tienda
                productRepository.saveAll(createProductsForStore1(store1));
                productRepository.saveAll(createProductsForStore2(store2));
                productRepository.saveAll(createProductsForStore3(store3));
                productRepository.saveAll(createProductsForStore4(store4));
                productRepository.saveAll(createProductsForStore5(store5));
            }
        };
    }

    private Store createStore(String name, String category, String city, double rating, String image) {
        Store store = new Store();
        store.setName(name);
        store.setCategory(category);
        store.setCity(city);
        store.setRating(rating);
        store.setImage(image);
        return store;
    }

    private List<Product> createProductsForStore1(Store store) {
        return Arrays.asList(
                createProduct("Spaghetti Bolognese", 12.99, "spaghetti_bolognese.png", store),
                createProduct("Lasagna", 14.99, "lasagna.png", store),
                createProduct("Pesto Penne", 11.49, "pesto_penne.png", store),
                createProduct("Tiramisu", 7.99, "tiramisu.png", store),
                createProduct("Garlic Bread", 4.99, "garlic_bread.png", store),
                createProduct("Fettuccine Alfredo", 13.99, "fettuccine_alfredo.png", store)
        );
    }

    private List<Product> createProductsForStore2(Store store) {
        return Arrays.asList(
                createProduct("Cheeseburger", 8.99, "cheeseburger.png", store),
                createProduct("Bacon Burger", 10.49, "bacon_burger.png", store),
                createProduct("Chicken Nuggets (10 pcs)", 6.99, "chicken_nuggets.png", store),
                createProduct("Fries (Large)", 3.49, "fries_large.png", store),
                createProduct("Chocolate Milkshake", 4.99, "chocolate_milkshake.png", store),
                createProduct("Veggie Burger", 9.49, "veggie_burger.png", store)
        );
    }

    private List<Product> createProductsForStore3(Store store) {
        return Arrays.asList(
                createProduct("California Roll", 9.99, "california_roll.png", store),
                createProduct("Salmon Nigiri", 12.49, "salmon_nigiri.png", store),
                createProduct("Tuna Sashimi", 14.99, "tuna_sashimi.png", store),
                createProduct("Miso Soup", 4.99, "miso_soup.png", store),
                createProduct("Edamame", 3.99, "edamame.png", store),
                createProduct("Dragon Roll", 15.49, "dragon_roll.png", store)
        );
    }

    private List<Product> createProductsForStore4(Store store) {
        return Arrays.asList(
                createProduct("Chicken Tacos", 8.99, "chicken_tacos.png", store),
                createProduct("Beef Burrito", 9.99, "beef_burrito.png", store),
                createProduct("Quesadilla", 7.99, "quesadilla.png", store),
                createProduct("Churros", 5.49, "churros.png", store),
                createProduct("Guacamole & Chips", 4.99, "guacamole_chips.png", store),
                createProduct("Taco Salad", 10.49, "taco_salad.png", store)
        );
    }

    private List<Product> createProductsForStore5(Store store) {
        return Arrays.asList(
                createProduct("Pepperoni Pizza", 12.99, "pepperoni_pizza.png", store),
                createProduct("Margherita Pizza", 11.49, "margherita_pizza.png", store),
                createProduct("BBQ Chicken Pizza", 13.99, "bbq_chicken_pizza.png", store),
                createProduct("Vegetarian Pizza", 10.99, "vegetarian_pizza.png", store),
                createProduct("Garlic Breadsticks", 5.99, "garlic_breadsticks.png", store),
                createProduct("Hawaiian Pizza", 12.49, "hawaiian_pizza.png", store)
        );
    }

    private Product createProduct(String name, double price, String image, Store store) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setImage(image);
        product.setStore(store);
        return product;
    }
}
