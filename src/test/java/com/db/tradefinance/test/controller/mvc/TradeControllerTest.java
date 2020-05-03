package com.db.tradefinance.test.controller.mvc;

import com.db.tradefinance.common.utils.DateUtils;
import com.db.tradefinance.controller.mvc.TradeController;
import com.db.tradefinance.dao.impl.TradeDaoImpl;
import com.db.tradefinance.model.Book;
import com.db.tradefinance.model.Trade;
import com.db.tradefinance.service.impl.BookServiceImpl;
import com.db.tradefinance.service.impl.TradeServiceImpl;
import com.db.tradefinance.test.config.TestBeanConfig;
import static org.mockito.Mockito.when;


import com.db.tradefinance.validation.TradeValidations;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestBeanConfig.class})
public class TradeControllerTest {
    private static final Logger LOG = LoggerFactory.getLogger(TradeController.class);

    private MockMvc mockMvc;
    /*@InjectMocks
    TradeServiceImpl tradeService;*/

    @Mock
    TradeServiceImpl tradeService;
    @Mock
    BookServiceImpl bookService;
    @Mock
    TradeValidations tradeValidations;
    @InjectMocks
    private TradeController tradeController;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(tradeController).build();
    }


    @Test
    public void listPageTest() throws Exception {
        List<Trade> trades = new ArrayList<Trade>();
        Trade trade = new Trade();
        trade.setId("6");
        trade.setTradeId("T1");
        trade.setVersion(2);
        trade.setMaturityDate(DateUtils.currentDate());
        trades.add(trade);

        trade = new Trade();
        trade.setId("5");
        trade.setTradeId("T1");
        trade.setVersion(1);
        trade.setMaturityDate(DateUtils.currentDate());
        int pageNo =0;
        trades.add(trade);
        when(tradeService.getAll(pageNo)).thenReturn(trades);

        LOG.info("Mock MVC "+mockMvc);


        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(forwardedUrl("list"))
                .andExpect(model().attribute("trades", hasSize(2)))
                .andExpect(model().attribute("trades", hasItem(
                        allOf(
                                hasProperty("tradeId", is("T1")),
                                hasProperty("version", is(2)),
                                hasProperty("id", is("6"))
                        )
                )))
                .andExpect(model().attribute("trades", hasItem(
                        allOf(
                                hasProperty("tradeId", is("T1")),
                                hasProperty("version", is(1)),
                                hasProperty("id", is("5"))
                        )
                )));

        LOG.info("Mock MVC perfomred" );
        verify(tradeService, times(1)).getAll(pageNo);
        verifyNoMoreInteractions(tradeService);
    }

    @Test
    public void savePageTest() throws Exception {

        List<Book> books = new ArrayList<Book>();
        Trade trade = new Trade();
        trade.setId("6");
        trade.setTradeId("T1");
        trade.setVersion(2);
        trade.setMaturityDate(DateUtils.currentDate());


        Book r1 = new Book();
        r1.setId("B1");
        books.add(r1);
        Book r2 = new Book();
        r2.setId("B2");
        books.add(r2);


        when(tradeService.getById("6")).thenReturn(trade);
        when(bookService.getAll()).thenReturn(books);

        LOG.info("Mock MVC "+mockMvc);


        mockMvc.perform(get("/savePage?q=6"))
                .andExpect(status().isOk())
                .andExpect(view().name("save"))
                .andExpect(forwardedUrl("save"))
                .andExpect(model().attribute("trade",
                                hasProperty("tradeId", is("T1"))))
                .andExpect(model().attribute("trade",
                        hasProperty("version", is(2))))
                .andExpect(model().attribute("trade",
                        hasProperty("id", is("6"))))

                .andExpect(model().attribute("bookList", hasSize(2)))
                .andExpect(model().attribute("bookList", hasItem(
                        allOf(
                                hasProperty("id", is("B1"))
                        )
                )))
                .andExpect(model().attribute("bookList", hasItem(
                        allOf(
                                hasProperty("id", is("B2"))
                        )
                )))        ;

        LOG.info("Mock MVC perfomred" );
        verify(tradeService, times(1)).getById("6");
        verifyNoMoreInteractions(tradeService);
        verify(bookService, times(1)).getAll();
        verifyNoMoreInteractions(bookService);
    }


    @Test
    public void saveTradeTest() throws Exception {
        Trade trade = new Trade();
        trade.setId("6");
        trade.setTradeId("T1");
        trade.setVersion(2);
        trade.setCounterPartyID("CP-1");
        trade.setBookid("BI1");
        trade.setMaturityDate(DateUtils.currentDate());
        String expectedURL = "";
        LOG.info("Mock MVC "+mockMvc);
        when(tradeService.tradeVersionUpsert(trade)).thenReturn(true);

        expectedURL="redirect:/";
        mockMvc.perform(post("/savePage")
                .flashAttr("trade", trade))
                .andExpect(status().isFound())
                .andExpect(view().name(expectedURL))
                .andExpect(redirectedUrl("/"));

        LOG.info("Mock MVC perfomred" );
        verify(tradeService, times(1)).tradeVersionUpsert(trade);
        verifyNoMoreInteractions(tradeService);

    }

    @Test
    public void saveTradeFailTest() throws Exception {
        Trade trade = new Trade();
        performOperationTradePost(trade);
        trade.setId("6");
        performOperationTradePost(trade);
        trade.setTradeId("T1");
        performOperationTradePost(trade);
        trade.setVersion(2);
        performOperationTradePost(trade);
        trade.setCounterPartyID("CP-1");
        performOperationTradePost(trade);
        trade.setBookid("BI1");
        performOperationTradePost(trade);
        trade.setMaturityDate(DateUtils.currentDate());
        trade.setTradeId("");
        performOperationTradePost(trade);
        //performOperationTradePost(trade);
        LOG.info("Mock MVC "+mockMvc);





    }
    private void performOperationTradePost(Trade trade) throws Exception {
        String expectedURL = "save";
        when(tradeService.tradeVersionUpsert(trade)).thenReturn(true);
        mockMvc.perform(post("/savePage")
                .flashAttr("trade", trade))
                .andExpect(status().isOk())
                .andExpect(view().name(expectedURL))
                .andExpect(forwardedUrl(expectedURL));

        LOG.info("Mock MVC perfomred");
        verify(tradeService, times(0)).tradeVersionUpsert(trade);
        verifyNoMoreInteractions(tradeService);

    }
}