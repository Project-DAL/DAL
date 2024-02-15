package kr.co.Dal.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCondition {
    protected Map<String, String> map;

    //페이징 관련
    private Integer page = 1;
    private Integer pageSize = 10;
    private Integer no = 0;
    private String title;
    private String uid;

    //검색 관련
    private String searchKeyword;
    private String searchValue;
    private int bardType;

    //추가
    private int userId;


    public String getQueryString(Integer page) {
        return getQueryString(page, no);
    }

    public String getQueryString(Integer page, Integer no) {
        UriComponentsBuilder builder = getDefaultBuilder(page, no);

        if (map != null) {
            for (String key : map.keySet()) {
                String value = map.get(key);

                if (!"page".equals(key) && !"no".equals(key) && !"searchKeyword".equals(key) && !"searchValue".equals(key)) {
                    if (value != null && !value.isBlank())
                        builder.queryParam(key, value);
                }
            }
        }

        return builder.toUriString();
    }

    public String getQueryString() {
        return getQueryString(page);
    }

    public UriComponentsBuilder getDefaultBuilder(Integer page, Integer no) {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance().queryParam("page", page);

        if (no != null && no != 0) {
            builder.queryParam("no", no);
        }

        if (searchKeyword != null && !searchKeyword.isBlank()) {
            builder.queryParam("searchField", getSearchKeyword())
                    .queryParam("searchWord", getSearchValue())
                    .queryParam("bardType", getSearchValue());
        }

        return builder;
    }

    public Integer getOffset() {
        return (page - 1) * pageSize;
    }

    public void setPage(Integer page) {
        this.page = page == 0 ? 1 : page;
    }
}
