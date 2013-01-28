package br.com.cygnus.exemplos.business;

import br.com.cygnus.framework.IObjetoGenerico;
import br.com.cygnus.framework.template.business.dto.AbstractDTO;

/**
 * Contrato de manipulacao de objetos em banco de dados.
 * 
 * @param <D> Objetos de transferencia de dados.
 */
public interface DataManipulation<D extends AbstractDTO> extends IObjetoGenerico {

   /**
    * Cria um novo registro.
    * 
    * @param dto dados do registro a incluir.
    */
   void create(D dto);

   /**
    * Atualiza um registro existente.
    * 
    * @param dto dados do registro a atualizar.
    */
   void update(D dto);

   /**
    * Exclui um registro existente.
    * 
    * @param dto dados do registro a excluir.
    */
   void delete(D dto);
}
