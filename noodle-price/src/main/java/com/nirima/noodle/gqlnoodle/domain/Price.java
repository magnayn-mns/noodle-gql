package com.nirima.noodle.gqlnoodle.domain;

import java.util.UUID;

public class Price {
   public String markedPrice = "9.99";
   public String nowUnitPrice = "9.99";
   public String  priceBeforeConditionalDiscounts = "9.99";
   public String  priceBeforeDiscounts= "9.99";
   public String  wasUnitPrice = "9.99";

   public Price() {
   }

   public Price(String amt) {
      markedPrice = amt;
      nowUnitPrice = amt;
      priceBeforeConditionalDiscounts = amt;
      priceBeforeDiscounts = amt;
      wasUnitPrice = amt;

   }
}
