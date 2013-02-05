package br.com.cygnus.exemplos.business;

import br.com.cygnus.exemplos.commons.dto.LivroDTO;
import br.com.cygnus.exemplos.commons.dto.LivroFiltroDTO;

public interface LivroService extends DataQuery<LivroFiltroDTO, LivroDTO>, DataManipulation<LivroDTO> {

}
