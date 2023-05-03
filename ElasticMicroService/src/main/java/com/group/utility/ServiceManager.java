package com.group.utility;


import com.group.repository.entity.BaseEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public class ServiceManager<T extends BaseEntity, ID> implements IService<T, ID> {

    private final ElasticsearchRepository<T, ID> repository;

    public ServiceManager(ElasticsearchRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T save(T t) {
        long time = System.currentTimeMillis();
        t.setIsactive(true);
        t.setCreateat(time);
        t.setUpdateat(time);
        return repository.save(t);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> t) {
        t.forEach(x -> {
            long time = System.currentTimeMillis();
            x.setCreateat(time);
            x.setUpdateat(time);
            x.setIsactive(true);
        });
        return repository.saveAll(t);
    }

    @Override
    public T update(T t) {
        t.setUpdateat(System.currentTimeMillis());
        return repository.save(t);
    }

    @Override
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<T> findAll() {
        return repository.findAll();
    }
}
