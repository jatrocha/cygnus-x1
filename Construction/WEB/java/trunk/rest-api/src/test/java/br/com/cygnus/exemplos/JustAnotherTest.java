package br.com.cygnus.exemplos;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cygnus.exemplos.business.impl.LivroBusiness;
import br.com.cygnus.exemplos.commons.dto.LivroDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JustAnotherTest {

   @Resource
   private LivroBusiness business;

   @Test
   public void test() {

      List<LivroDTO> findAll = this.business.findAll();

      assertEquals(Integer.valueOf(5), Integer.valueOf(findAll.size()));
   }
}
