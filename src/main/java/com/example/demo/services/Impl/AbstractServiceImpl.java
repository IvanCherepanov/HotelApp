package com.example.demo.services.Impl;

import com.example.demo.model.dao.IAbstractRepository;
import com.example.demo.services.IAbstractService;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.data.domain.Sort;

import java.util.List;

public abstract class AbstractServiceImpl <T, D extends IAbstractRepository<T>> implements IAbstractService<T> {
    private D defaultDao;

    protected AbstractServiceImpl(D defaultDao) {
        this.defaultDao = defaultDao;
    }

    @Override
    public T create(T entity) {
        return defaultDao.save(entity);
    }


    // TODO: 28.04.2022 подумать: иначе возврат нуля или собственного исключения; findById or getById
    /**
     * @see <a href="https://stackoverflow.com/questions/69109649/hibernate-findbyid-vs-getbyid"></a>
     * */
    @Override
    public T findById(Long id) {
        return (T) defaultDao.getById(id);
    }

    /**
     * Если нет исключения, то просто перепишем сущность
     * */
    @Override
    public T update(Long id, T entity) {
        findById(id);
        return  create(entity);
    }

    @Override
    public boolean delete(Long id) {
        if (findById(id) == null) return false;
        defaultDao.deleteById(id);
        return true;
    }

    @Override
    public List<T> getAll() {
        Sort sort = Sort.by("id").ascending();//не очень красиво и правильно, но
        System.out.println("here getAll failes");
        return defaultDao.findAll(sort);
    }

}

