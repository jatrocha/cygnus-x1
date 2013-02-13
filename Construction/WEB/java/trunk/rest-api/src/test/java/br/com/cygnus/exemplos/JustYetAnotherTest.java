package br.com.cygnus.exemplos;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cygnus.exemplos.business.impl.LivroBusiness;
import br.com.cygnus.exemplos.commons.dto.LivroDTO;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JustYetAnotherTest {

   @Resource
   private LivroBusiness business;

   @Test
   public void test() {

      this.business.create(LivroDTO.buildWith("Titulo", "autor", "genero"));

   }
}
