package br.com.cygnus.exemplos.business;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.cygnus.exemplos.commons.dto.CarroDTO;
import br.com.cygnus.exemplos.datastore.CarroDataStore;

/**
 * Negocio para manipulacao de {@link CarroDTO}.
 */
@Service
public class CarroManipulationBusiness implements DataManipulation<CarroDTO> {

   //   @Resource(name="carroDataStore")
   //   private DataStore<Carro> dataStore;

   @Resource
   private CarroDataStore dataStore;


   /**
    * @see br.com.cygnus.exemplos.business.DataManipulation#create(br.com.cygnus.framework.template.business.dto.AbstractDTO).
    */
   @Override
   public void create(CarroDTO dto) {

   }

   /**
    * @see br.com.cygnus.exemplos.business.DataManipulation#update(br.com.cygnus.framework.template.business.dto.AbstractDTO).
    */
   @Override
   public void update(CarroDTO dto) {

   }

   /**
    * @see br.com.cygnus.exemplos.business.DataManipulation#delete(br.com.cygnus.framework.template.business.dto.AbstractDTO).
    */
   @Override
   public void delete(CarroDTO dto) {

   }

   /**
    * @param dataStore
    */
   protected final void setDataStore(final CarroDataStore dataStore) {

      this.dataStore = dataStore;
   }

}
