package com.example.demo.services.Impl;

import com.example.demo.model.dao.ICardRepository;
import com.example.demo.model.dao.ICleaningRepository;
import com.example.demo.model.dao.IFoodRepository;
import com.example.demo.model.entity.Card;
import com.example.demo.model.entity.Room;
import com.example.demo.services.ICardService;
import com.example.demo.services.ICleaningService;
import com.example.demo.services.IFoodService;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CardServiceImpl extends AbstractServiceImpl<Card, ICardRepository> implements ICardService {
    private ICardRepository iCardRepository;
    private IFoodRepository iFoodRepository;
    private ICleaningRepository iCleaningRepository;
    @Autowired
    protected CardServiceImpl(ICardRepository defaultDao,IFoodRepository iFoodRepository,ICleaningRepository iCleaningRepository) {
        super(defaultDao);
        iCardRepository=defaultDao;
        this.iFoodRepository = iFoodRepository;
        this.iCleaningRepository = iCleaningRepository;
    }

    @Override
    public List<Object[]> getListByParam(LocalDate inputDate, LocalDate outputDate, int capacity) {
        return iCardRepository.getListByCapacityAndRange(inputDate,outputDate,capacity);
    }

    @Override
    public void create(LocalDate InputDate, LocalDate OutputDate, Long idClient, Long idRoom) {
        Card card = new Card();
        card.setInputDate(InputDate);
        card.setOutputDate(OutputDate);
        card.setClientId(idClient);
        card.setRoomId(idRoom);
        card.setFoodId(iFoodRepository.getFoodByDescription("Обед").getId());
        card.setCleaningId(iCleaningRepository.findCleaningByDescription("Утрення уборка").getId());
        iCardRepository.save(card);
    }

    @Override
    public List<Card> getListByClientId(Long id) {
        return iCardRepository.findAllByClientId(id);
    }

    @Override
    public List<Object[]> getListRoomCostById(Long id) {
        return iCardRepository.getRoomCostById(id);
    }

    @Override
    public Object[] getObjectById(Long id,  List<Object[]> fullList ) {
        Object[] answer = null;

        for (Object[] temp: fullList
             ) {
            System.out.println(Long.valueOf(temp[10].toString()) == id);
            System.out.println("_________________");
            if (Long.valueOf(temp[10].toString()) == id){
                answer = temp;
                return temp;
            }
        }
        return answer;
    }
}
