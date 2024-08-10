package com.gechan.openmarket.repository;

import com.gechan.openmarket.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /*
    productImageList 테이블을 따로 조회하지 않고
    @EntitiyGraph 애노테이션을 통해 한번에 조인해서 조회해오기
    Product 테이블과 productImageListr 테이블을 두번 조회할 필요가 없어진다.
     */
    @EntityGraph(attributePaths = "productImageList")
    @Query("select p from Product p where p.pno = :pno")
    Optional<Product> selectOne(@Param("pno") Long pno);

    /*
    productImageList와 left join
    pi의 ord == 0 -> thumbnail 이미지로 보여주기 위한 첫번째 사진 가져오기
    sellFlag : 판매중인 것
    페이징 처리를 위해 Pageable을 받고 Page 객체 반환
     */
    @Query("select p, pi from Product p left join p.productImageList pi where pi.ord = 0 and p.sellFlag = false")
    Page<Object[]> selectList(Pageable pageable);
}
