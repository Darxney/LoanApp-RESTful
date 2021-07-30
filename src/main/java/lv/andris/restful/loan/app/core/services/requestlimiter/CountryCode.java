package lv.andris.restful.loan.app.core.services.requestlimiter;

public class CountryCode {

        private String countryCode;

        public CountryCode() {

        }
        public CountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public String setCountryCode(String countryCode) {
            this.countryCode = countryCode;
            return countryCode;
        }
    }
