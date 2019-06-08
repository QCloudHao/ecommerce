package com.project.ecommerce.service.impl;

import com.project.ecommerce.bean.Article;
import com.project.ecommerce.bean.ArticleType;
import com.project.ecommerce.bean.User;
import com.project.ecommerce.repository.ArticleMapper;
import com.project.ecommerce.repository.ArticleTypeMapper;
import com.project.ecommerce.repository.UserMapper;
import com.project.ecommerce.service.ShopService;
import com.project.ecommerce.utils.Pager;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qyh on 2019/6/7.
 */
@Service("shopService")
public class ShopServiceImpl implements ShopService {

    // 得到数据访问层对象
    @Resource
    private ArticleTypeMapper articleTypeMapper;
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public List<ArticleType> getArticleTypes() {
        return articleTypeMapper.getArticleTypes();
    }

    @Override
    public Map<String, Object> login(String loginName, String passWord) {
        Map<String,Object> results = new HashMap<>();
        // 判断参数是否为空
        if(StringUtils.isEmpty(loginName) || StringUtils.isEmpty(passWord)){
            // 参数为空
            results.put("code",1);
            results.put("msg","参数为空");
        }else{
            // 根据登录名称去查询用户对象
            User user = userMapper.login(loginName);
            if(user!=null){
                // 判断密码
                if(user.getPassword().equals(passWord)){
                    // 登陆成功了
                    // 应该将登陆成功的用户存入到Session会话中
                    results.put("code",0);
                    results.put("msg",user);
                }else{
                    // 密码错误
                    results.put("code",2);
                    results.put("msg","密码错误");
                }
            }else{
                // 用户名不存在
                results.put("code",3);
                results.put("msg","登录名不存在");
            }
        }
        return results;
    }

    @Override
    public List<ArticleType> loadFirstArticleTypes() {
        List<ArticleType> articleTypes = articleTypeMapper.getFirstArticleTypes();
        return articleTypes;
    }

    @Override
    public List<ArticleType> loadSecondTypes(String typeCode) {
        List<ArticleType> articleTypes = articleTypeMapper.loadSecondTypes(typeCode+"%",typeCode.length()+4);
        return articleTypes;
    }

    @Override
    public List<Article> searchArticles(String typeCode, String secondType, String title, Pager pager) {
        // 界面需要当前总共有多少条数据
        // 查询当前条件下总共有多少条数据
        int count = articleMapper.count(typeCode, secondType, title);
        pager.setTotalCount(count);
        return articleMapper.searchArticles(typeCode, secondType, title, pager);
    }

    @Override
    public void deleteById(String id) {
        articleMapper.deleteById(id);
    }

    @Override
    public Article getArticleById(String id) {
        return articleMapper.getArticleById(id);
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.update(article);
    }

    @Override
    public void saveArticle(Article article) {
        article.setCreateDate(new Date());
        articleMapper.save(article);
    }
}
