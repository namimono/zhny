package org.rcisoft.business.whole.menu.service.impl;

import org.rcisoft.base.jwt.JwtTokenUtil;
import org.rcisoft.business.whole.menu.dao.UserRoleDao;
import org.rcisoft.business.whole.menu.entity.ChildMenu;
import org.rcisoft.business.whole.menu.entity.FirstMenu;
import org.rcisoft.business.whole.menu.entity.UserRoleMenuDto;
import org.rcisoft.business.whole.menu.service.UserRoleService;
import org.rcisoft.entity.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRoleDao userRoleDao;
    @Override
    public List<FirstMenu> userRoleMenu(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        List<UserRoleMenuDto> userRoleMenuDtos = userRoleDao.userRoleMenu(username);
        /**
         * 对数据进行排序和组装
         *
         */
        List<FirstMenu> listFirstMenu = new ArrayList<FirstMenu>();
        FirstMenu firstMenu =null;
        ChildMenu childMenu = null;
        List<ChildMenu> childMenuList = null;

        for(int i=0;i<userRoleMenuDtos.size();i++){
            if(userRoleMenuDtos.get(i).getLevel()!=1) {
                continue;
            }else{
                firstMenu =  new FirstMenu();
                firstMenu.setName(userRoleMenuDtos.get(i).getName());
                firstMenu.setUrl(userRoleMenuDtos.get(i).getUrl());
                firstMenu.setOrdered(userRoleMenuDtos.get(i).getOrdered());
                childMenuList = new ArrayList<ChildMenu>();
                for(int j=0;j<userRoleMenuDtos.size();j++){
                    if(userRoleMenuDtos.get(j).getPid()==userRoleMenuDtos.get(i).getId()
                            && userRoleMenuDtos.get(j).getLevel()==2){
                        childMenu= new ChildMenu();
                        childMenu.setName( userRoleMenuDtos.get(j).getName());
                        childMenu.setUrl( userRoleMenuDtos.get(j).getUrl());
                        childMenu.setOrdered( userRoleMenuDtos.get(j).getOrdered());
                        childMenuList.add(childMenu);
                    }
                }
                Collections.sort(childMenuList);
                firstMenu.setChildMenuList(childMenuList);
            }

            listFirstMenu.add(firstMenu);
        }
    Collections.sort(listFirstMenu);
        return listFirstMenu;
    }

}
