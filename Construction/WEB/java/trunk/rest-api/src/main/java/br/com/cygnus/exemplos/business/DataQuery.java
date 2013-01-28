package br.com.cygnus.exemplos.business;

import java.util.List;

import br.com.cygnus.framework.IObjetoGenerico;
import br.com.cygnus.framework.template.business.dto.AbstractDTO;
import br.com.cygnus.framework.template.business.dto.AbstractFilterDTO;

/**
 * Contrato para implementar classes de negocio que se fazem consulta de dados.
 * 
 * @param <D> Objetos de transferencia de dados.
 * @param <F> Objeto de transferencia para consultas e/ou pesquisas.
 */
public interface DataQuery<F extends AbstractFilterDTO, D extends AbstractDTO> extends IObjetoGenerico {

   /**
    * Le um registro a partir da sua chave prim‡ria.
    * 
    * @param dto filtro para busca.
    * @return registro encontrado.
    */
   D read(F dto);

   /**
    * Recupera uma lista de registros, partir do filtro informado.
    * 
    * @return lista de registros.
    */
   List<D> list();

   /**
    * Busca registros de acordo com um determinado filtro.
    * 
    * @param dto filtro para busca.
    * @return lista de registros.
    */
   List<D> findBy(F dto);
}
