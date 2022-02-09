package com.truper.spring.core.practicaE.propertyeditor.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

import com.truper.spring.core.practicaE.propertyeditor.bean.CreditCard;

// Extiende PropertyEditorSupport
public class CreditCardEditor extends PropertyEditorSupport {

	@Override
	public String getAsText() {
		CreditCard cc = (CreditCard) this.getValue();

		return cc == null ? "" : cc.getRawCardNumber();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		CreditCard cc = this.parseCreditCardFromText(text);

		this.setValue(cc);
	}

	public CreditCard parseCreditCardFromText(String text) {
		if (StringUtils.isEmpty(text)) {

			return null;

		} else {

			CreditCard.CreditCardBuilder creditCardBuilder = CreditCard.builder();

			creditCardBuilder.rawCardNumber(text);

			String cardNo = text.replaceAll("-", "");

			if (cardNo.length() != 16)
				throw new IllegalArgumentException("Invalid credit card format, should be: xxxx-xxxx-xxxx-xxxx");

			try {
				creditCardBuilder.bankIdNo(Integer.valueOf(cardNo.substring(0, 6)))
						.accountNo(Integer.valueOf(cardNo.substring(6, cardNo.length() - 1)))
						.checkCode(Integer.valueOf(cardNo.substring(cardNo.length() - 1)));

			} catch (NumberFormatException nfe) {
				throw new IllegalArgumentException(nfe);
			}

			return creditCardBuilder.build();
		}
	}
}