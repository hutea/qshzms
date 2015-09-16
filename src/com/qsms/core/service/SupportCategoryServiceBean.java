package com.qsms.core.service;

import org.springframework.stereotype.Service;

import com.qsms.core.ebean.SupportCategory;
import com.qsms.util.dao.DAOSupport;

@Service
public class SupportCategoryServiceBean extends DAOSupport<SupportCategory>
		implements SupportCategoryService {

}
