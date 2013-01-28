package br.com.cygnus.exemplos.business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.cygnus.exemplos.commons.dto.CarroDTO;
import br.com.cygnus.exemplos.commons.dto.CarroFilterDTO;
import br.com.cygnus.exemplos.datastore.CarroDataStore;
import br.com.cygnus.exemplos.persistence.model.Carro;
import br.com.cygnus.framework.template.business.converter.Converter;

/**
 * Regras de negocio para busca e listagem de {@link CarroDTO}.
 */
@Service
public class CarroQueryBusiness implements DataQuery<CarroFilterDTO, CarroDTO> {

   @Resource
   private CarroDataStore dataStore;

   /**
    * @see br.com.cygnus.exemplos.business.DataQuery#read(br.com.cygnus.framework.template.business.dto.AbstractFilterDTO).
    */
   @Override
   public CarroDTO read(CarroFilterDTO dto) {

      return null;
   }

   /**
    * @see br.com.cygnus.exemplos.business.DataQuery#list().
    */
   @Override
   @Transactional(propagation = Propagation.NEVER)
   public List<CarroDTO> list() {

      final List<Carro> list = this.dataStore.list();

      return new CarroListConverter().convert(list);
   }

   /**
    * @see br.com.cygnus.exemplos.business.DataQuery#findBy(br.com.cygnus.framework.template.business.dto.AbstractFilterDTO).
    */
   @Override
   public List<CarroDTO> findBy(CarroFilterDTO dto) {

      return null;
   }

   /**
    * @param dataStore {@link CarroDataStore}.
    */
   protected final void setDataStore(final CarroDataStore dataStore) {

      this.dataStore = dataStore;
   }

   /**
    * Conversor de listas: de {@link Carro} para {@link CarroDTO}.
    */
   class CarroListConverter implements Converter<List<Carro>, List<CarroDTO>> {

      /**
       * @see br.com.cygnus.framework.template.business.converter.Converter#convert(java.lang.Object).
       */
      @Override
      public List<CarroDTO> convert(List<Carro> source) {

         List<CarroDTO> retorno = new ArrayList<CarroDTO>();

         for (Carro carro : source) {

            retorno.add(CarroDTO.buildWith(carro.getId(), carro.getMarca().name(), carro.getModelo(), carro.getVersao(), carro.getMotor()));
         }

         return retorno;
      }
   }

}
