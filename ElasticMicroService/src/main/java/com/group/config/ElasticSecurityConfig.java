package com.group.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Configuration -> Konfigurasyon dosyasi olarak springe bildirecegimiz siniflara ekliyoruz.
 */

@Configuration
@RequiredArgsConstructor
public class ElasticSecurityConfig {

    @Bean
     JwtTokenFilter getTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        /**
         * _csrf -> bunu kapatmak icin disable komutunu kullaniyoruz.
         */
        httpSecurity.csrf().disable();
        /**
         * Gelen butun isteklere oturum acilmis mi? , kendini dogrulamis mi bakar.
         * Eger ozel adreslere erisimi acmak istiyorsaniz bunlari belirterek izin vermelisiniz.
         * Match("/{URLS}) icin izin  verilsin
         */
        httpSecurity.authorizeRequests()
                .antMatchers("/mylogin.html").permitAll()
                .anyRequest().authenticated();
        /**
         * Yetkisiz girislerde kullanicilarin kendilerini dogrulamalari icin login formuna yonlendirme yaoaruz,
         */
//        httpSecurity.formLogin();
        /**
         * Eger kullaniciya kendi login formunuzu gostermek istiyorsaniz, o zaman kendi
         * formunuza izin vererek yonlendirme islemi yapmalisiniz
         */
//        httpSecurity.formLogin().loginPage("/mylogin.html");
        /**
         * Auth servis uzerinden kendisini dogrulayan bir kisinin isteklerinin nasil isleyecegini cozmemiz gerekiyor.
         * Kisinin elinde olan token bilgisi okunarak  bu kisiye yetkileri dahilinde gecerli olan token uzerinden
         * oturum izni verilecek, boylece kisi her seferinde kendini dogrulamak zorunda kalmayacak.
         * Bunu basarmak icin oncelikle filter isleminin oncesi icin bir islem adimi sokarak kisiyi sorgulayacagiz.
         */

        httpSecurity.addFilterBefore(getTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }


}
