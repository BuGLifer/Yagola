package com.buglifer.yagola.menu.service;

import com.buglifer.yagola.common.criteria.Criteria;
import com.buglifer.yagola.common.domain.MenuEntity;
import com.buglifer.yagola.menu.dto.MenuDTO;
import com.buglifer.yagola.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuDTO> getAllMenu(Long restSeq) {
        List<MenuEntity> optionalMenuEntity;
        if (restSeq > 0) {
            optionalMenuEntity = menuRepository.findAllByRestaurant(restSeq);
        } else {
            optionalMenuEntity = menuRepository.findAll();
        }
        return null;
    }
}
