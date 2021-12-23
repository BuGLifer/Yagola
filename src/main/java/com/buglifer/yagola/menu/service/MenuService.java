package com.buglifer.yagola.menu.service;

import com.buglifer.yagola.common.domain.MenuEntity;
import com.buglifer.yagola.menu.dto.MenuDTO;
import com.buglifer.yagola.menu.repository.MenuRepository;
import com.buglifer.yagola.menu.search.MenuSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public Page<MenuDTO> findMenuBySearch(MenuSearch search) {
        Page<MenuEntity> menuEntityList;
        if (search.getRestSeq() == 0) {
            menuEntityList = menuRepository.findAll(search.toPageable());
        } else {
            menuEntityList = menuRepository.findAll(search.toSpec(), search.toPageable());
        }
        return menuEntityList
                .map(
                        e -> MenuDTO
                            .fromEntity()
                            .entity(e)
                            .build()
                );
    }

    public MenuDTO findMenuBySeq(long seq) {
        Optional<MenuEntity> optionalMenuEntity = menuRepository.findById(seq);
        if(!optionalMenuEntity.isPresent()) throw new EntityNotFoundException("해당 메뉴가 존재하지 않습니다.");
        return MenuDTO
                .fromEntity()
                .entity(optionalMenuEntity.get())
                .build();
    }

    public MenuDTO saveMenu(MenuDTO menuDTO) {
        MenuEntity menuEntity = MenuEntity
                .fromDTO()
                .dto(menuDTO)
                .build();
        return MenuDTO
                .fromEntity()
                .entity(menuRepository.save(menuEntity))
                .build();
    }

    public void removeMenu(long menuSeq) {
        Optional<MenuEntity> menuEntity = menuRepository.findById(menuSeq);
        if (!menuEntity.isPresent()) throw new EntityNotFoundException("해당 메뉴가 존재하지 않습니다.");
        menuRepository.delete(menuEntity.get());
    }
}
