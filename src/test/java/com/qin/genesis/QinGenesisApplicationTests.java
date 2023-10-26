package com.qin.genesis;

import com.qin.genesis.common.PageResult;
import com.qin.genesis.entity.*;
import com.qin.genesis.repository.*;
import com.qin.genesis.service.IEnterpriseService;
import com.qin.genesis.util.SerializeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
class QinGenesisApplicationTests {

    @Autowired
    private DemandRepository demandRepository;

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private FinancingMoneyRepository financingMoneyRepository;

    @Autowired
    private QualificationRepository qualificationRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private IEnterpriseService enterpriseService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void initData() {
//        List<UniversityEntity> universityEntities = new ArrayList<>();
//        universityEntities.add(UniversityEntity.builder().universityName("长安大学").build());
//        universityEntities.add(UniversityEntity.builder().universityName("西安交通大学").build());
//        universityRepository.saveAll(universityEntities);
//
//        List<QualificationEntity> qualificationEntities = new ArrayList<>();
//        qualificationEntities.add(QualificationEntity.builder().qualificationName("高新技术企业").build());
//        qualificationEntities.add(QualificationEntity.builder().qualificationName("独角兽企业").build());
//        qualificationRepository.saveAll(qualificationEntities);
//
//        List<DemandEntity> demandEntities = new ArrayList<>();
//        demandEntities.add(DemandEntity.builder().category("产业与市场需求").subCategory("市场与产品定位").demandName("市场竞争洞察").build());
//        demandEntities.add(DemandEntity.builder().category("产业与市场需求").subCategory("市场与产品定位").demandName("abc市场竞争洞察").build());
//        demandEntities.add(DemandEntity.builder().category("产业与市场需求").subCategory("高新技术企业").demandName("高新-wefawe").build());
//        demandEntities.add(DemandEntity.builder().category("产业与市场需求").subCategory("高新技术企业").demandName("高新-opop").build());
//        demandEntities.add(DemandEntity.builder().category("空间政策需求").subCategory("空间政策").demandName("政策-opop").build());
//        demandEntities.add(DemandEntity.builder().category("空间政策需求").subCategory("空间政策").demandName("政策-opop").build());
//        demandRepository.saveAll(demandEntities);

        EnterpriseEntity entity = new EnterpriseEntity();
        entity.setId(3);
        entity.setContacts("awefawe");
        entity.setMemo("awefaweeeeeeeeeeeeeeeeeeeeeee");
        List<AttachmentEntity> attachmentEntities = new ArrayList<>();
        AttachmentEntity attachmentEntity = new AttachmentEntity();
        attachmentEntity.setId(6);
        attachmentEntity.setLink("http://abcddd");
        EnterpriseEntity one = new EnterpriseEntity();
        one.setId(3);
        attachmentEntity.setEnterprise(one);
        attachmentEntities.add(attachmentEntity);
        AttachmentEntity attachmentEntity2 = new AttachmentEntity();
//        attachmentEntity2.setId(7);
        attachmentEntity2.setLink("http://eeeedqqqq333333333322");
        attachmentEntity2.setEnterprise(entity);
        attachmentEntities.add(attachmentEntity2);
        entity.setAttachmentList(attachmentEntities);

        enterpriseRepository.save(entity);

//        AttachmentEntity attachmentEntity11 = attachmentRepository.findById(1).get();
//        System.out.println(SerializeUtil.serialize(attachmentEntity11));

//        EnterpriseEntity enterpriseEntity = enterpriseRepository.findById(3).orElse(new EnterpriseEntity());
//        System.out.println(SerializeUtil.serialize(enterpriseEntity));

//        enterpriseRepository.deleteById(2);
    }

    @Test
    void testSave() {
//        User abc1234 = userRepository.findByUsername("abc1234");
//        System.out.println(abc1234);

        Permission permission = new Permission();
        permission.setName("/premio");
        permissionRepository.save(permission);

        Role role = new Role();
        role.setName("普通");
        HashSet<Permission> set = new HashSet<>();
        set.add(permission);
        set.add(new Permission(3L));
        role.setPermissions(set);
        roleRepository.save(role);

        User user = new User();
        user.setUsername("user111");
        user.setPassword("123123");
        HashSet<Role> roles = new HashSet<>();
        roles.add(role);
        roles.add(new Role(2L));
        user.setRoles(roles);

        userRepository.save(user);
    }

    @Test
    void testQuery() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        List<Predicate> predicates = new ArrayList<>();
        //predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("username"), "user111"));

        query.select(root).where(predicates.toArray(new Predicate[0]));

        List<User> resultList = entityManager.createQuery(query)
                .setFirstResult(0)
                .setMaxResults(10)
                .getResultList();

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<User> countRoot = countQuery.from(User.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicates.toArray(new Predicate[0]));
        Long total = entityManager.createQuery(countQuery).getSingleResult();

        PageResult<User> pageResult = new PageResult<>();
        pageResult.setData(resultList);
        pageResult.setTotal(total);
        pageResult.setPages(1);
        System.out.println("===========total========= " + total);
        System.out.println(SerializeUtil.serialize(pageResult));
    }

}
