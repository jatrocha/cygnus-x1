package br.com.cygnus.exemplos.commons.dto;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.cygnus.framework.template.business.dto.AbstractFilterDTO;

/**
 * Filtro para pesquisas e buscas parametricas para {@link br.com.cygnus.exemplos.commons.dto.LivroDTO}.
 */
@XmlRootElement
public class LivroFilterDTO extends AbstractFilterDTO {

   private static final long serialVersionUID = 289779452196013173L;

}
