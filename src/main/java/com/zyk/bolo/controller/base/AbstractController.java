package com.zyk.bolo.controller.base;

import org.springframework.ui.Model;
public abstract class AbstractController {
	
	protected void detailPagesVo(Model model ,PagesVo<?> vo) {
		model.addAttribute("draw",vo.getDraw());
		model.addAttribute("recordsTotal",vo.getRecordsTotal());
		model.addAttribute("recordsFiltered",vo.getRecordsFiltered());
		model.addAttribute("data",vo.getData());
	}
//	protected void hasAnykRole(String... roleIdentifiers){
//
//		boolean error = true;
//		for(String role : roleIdentifiers){
//			if(SecurityUtils.getSubject().hasRole(role)){
//				error = false;
//				break;
//
//			}
//		}
//		if(error){
//			throw new ShiroException("ERROR ROLE");
//		}
//
//	}
}
