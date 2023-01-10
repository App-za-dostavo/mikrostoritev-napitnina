package si.fri.rso.napitnina.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.rso.napitnina.lib.Tip;
import si.fri.rso.napitnina.models.converters.TipConverter;
import si.fri.rso.napitnina.models.entities.TipEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;


@RequestScoped
public class TipBean {

    @Inject
    private EntityManager em;

    public List<Tip> getTips() {
        TypedQuery<TipEntity> query = em.createNamedQuery("TipEntity.getAll", TipEntity.class);

        List<TipEntity> resultList = query.getResultList();

        return resultList.stream().map(TipConverter::toDto).collect(Collectors.toList());
    }

    public List<Tip> getTipsFilter(UriInfo uriInfo) {


        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0).build();

        return JPAUtils.queryEntities(em, TipEntity.class, queryParameters).stream()
                .map(TipConverter::toDto).collect(Collectors.toList());
    }

    public Tip getTip(Integer id) {

        TipEntity tipEntity = em.find(TipEntity.class, id);

        if (tipEntity == null) {
            throw new NotFoundException();
        }

        Tip tip = TipConverter.toDto(tipEntity);

        return tip;
    }

    public Tip createTip(Tip tip) {

        TipEntity tipEntity = TipConverter.toEntity(tip);

        try {
            beginTx();
            em.persist(tipEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (tipEntity.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return TipConverter.toDto(tipEntity);
    }

    public Tip putTip(Integer id, Tip tip) {

        TipEntity data = em.find(TipEntity.class, id);

        if (data == null) {
            return null;
        }

        TipEntity updatedTipEntity = TipConverter.toEntity(tip);

        try {
            beginTx();
            updatedTipEntity.setId(data.getId());
            updatedTipEntity = em.merge(updatedTipEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return TipConverter.toDto(updatedTipEntity);
    }

    public boolean deleteTip(Integer id) {

        TipEntity tip = em.find(TipEntity.class, id);

        if (tip != null) {
            try {
                beginTx();
                em.remove(tip);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return false;
        }

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }

}
