package com.buglifer.yagola.menu.service;

import com.buglifer.yagola.common.domain.MenuEntity;
import com.buglifer.yagola.menu.dto.MenuDTO;
import com.buglifer.yagola.menu.repository.MenuRepository;
import com.buglifer.yagola.menu.search.MenuSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public Page<MenuDTO> findAllMenu(MenuSearch search) {
        Page<MenuEntity> menuEntityList;
        if (search.getRestSeq() == 0) {
            menuEntityList = menuRepository.findAll(search.toPageable());
        } else {
            menuEntityList = menuRepository.findAll(search.toSpec(), search.toPageable());
        }
        return menuEntityList
                .map(
                        e -> MenuDTO
                            .fromMenuEntity()
                            .menuEntity(e)
                            .build()
                );
    }

    public MenuDTO findMenu(long menuSeq) {
        Optional<MenuEntity> optionalMenuEntity = menuRepository.findById(menuSeq);
        return optionalMenuEntity.map(MenuDTO::new).orElseGet(MenuDTO::new);
    }

    public MenuDTO saveMenu(MenuDTO menuDTO) {
        MenuEntity menuEntity = MenuEntity
                .initMenu()
                .dto(menuDTO)
                .build();
        return new MenuDTO(menuRepository.save(menuEntity));
    }

    public long removeMenu(long menuSeq) {
        Optional<MenuEntity> menuEntity = menuRepository.findById(menuSeq);
        if (!menuEntity.isPresent()) {
            log.error("존재 하지 않는 메뉴 입니다.");
            return -1;
        }
        menuRepository.delete(menuEntity.get());
        return menuSeq;
    }
}
