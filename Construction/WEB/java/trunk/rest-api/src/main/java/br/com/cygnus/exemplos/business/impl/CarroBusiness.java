package br.com.cygnus.exemplos.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.cygnus.exemplos.business.DataManipulation;
import br.com.cygnus.exemplos.business.DataQuery;
import br.com.cygnus.exemplos.commons.dto.CarroDTO;
import br.com.cygnus.exemplos.commons.dto.CarroFilterDTO;
import br.com.cygnus.exemplos.datastore.CarroDataStore;
import br.com.cygnus.exemplos.persistence.model.Carro;
import br.com.cygnus.framework.template.business.converter.Converter;

@Service
public class CarroBusiness implements DataManipulation<CarroDTO>, DataQuery<CarroFilterDTO, CarroDTO> {

   @Resource
   private CarroDataStore dataStore;

   /**
    * Construtor padrao.
    */
   public CarroBusiness() {

      super();
   }

   /**
    * @param dataStore {@link CarroDataStore} injetado pelos testes unitarios.
    */
   protected CarroBusiness(CarroDataStore dataStore) {

      this();

      this.dataStore = dataStore;

   }

   /**
    * @see br.com.cygnus.exemplos.business.DataQuery#read(br.com.cygnus.framework.template.business.dto.AbstractFilterDTO).
    */
   @Override
   @Transactional(propagation = Propagation.NEVER)
   public CarroDTO read(CarroFilterDTO dto) {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see br.com.cygnus.exemplos.business.DataQuery#findAll().
    */
   @Override
   @Transactional(propagation = Propagation.NEVER)
   public List<CarroDTO> findAll() {

      final List<Carro> list = this.dataStore.list();

      return new CarroListConverter().convert(list);
   }

   /**
    * @see br.com.cygnus.exemplos.business.DataQuery#findBy(br.com.cygnus.framework.template.business.dto.AbstractFilterDTO)
    */
   @Override
   @Transactional(propagation = Propagation.NEVER)
   public List<CarroDTO> findBy(CarroFilterDTO dto) {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see br.com.cygnus.exemplos.business.DataManipulation#create(br.com.cygnus.framework.template.business.dto.AbstractDTO).
    */
   @Override
   public void create(CarroDTO dto) {
      // TODO Auto-generated method stub

   }

   /**
    * @see br.com.cygnus.exemplos.business.DataManipulation#update(br.com.cygnus.framework.template.business.dto.AbstractDTO).
    */
   @Override
   public void update(CarroDTO dto) {
      // TODO Auto-generated method stub

   }

   /**
    * @see br.com.cygnus.exemplos.business.DataManipulation#delete(br.com.cygnus.framework.template.business.dto.AbstractDTO).
    */
   @Override
   public void delete(CarroDTO dto) {
      // TODO Auto-generated method stub

   }

}

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