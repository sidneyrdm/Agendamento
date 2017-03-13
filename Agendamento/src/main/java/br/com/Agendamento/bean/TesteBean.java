package br.com.Agendamento.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

@ManagedBean
public class TesteBean {

	private List<SelectItem> categories;
	private String selection;

	@PostConstruct
	public void init() {
		categories = new ArrayList<SelectItem>();
		SelectItemGroup group3 = new SelectItemGroup("Group 3");


		SelectItem option31 = new SelectItem("Option 3.1", "Option 3.1");
		SelectItem option32 = new SelectItem("Option 3.2", "Option 3.2");

		group3.setSelectItems(new SelectItem[] { option31, option32});

		categories.add(group3);

	}

	public List<SelectItem> getCategories() {
		return categories;
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}
}