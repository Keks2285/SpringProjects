    package com.example.BarberShop.Repo;
    
    import com.example.BarberShop.Models.Provider;
    import org.springframework.data.repository.CrudRepository;
    
    import java.util.List;
    
    public interface ProviderRepository extends CrudRepository<Provider, Long> {
        List<Provider> findByNameproviderContains(String nameprovider);
    
        Provider findByInn(String inn);
    
        Provider findByNameprovider(String nameprovider);
    
    }
