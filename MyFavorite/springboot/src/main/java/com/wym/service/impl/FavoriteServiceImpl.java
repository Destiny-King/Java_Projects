package com.wym.service.impl;

import com.wym.entity.Favorite;
import com.wym.mapper.FavoriteMapper;
import com.wym.service.IFavoriteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wym
 * @since 2023-05-09
 */
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements IFavoriteService {

}
