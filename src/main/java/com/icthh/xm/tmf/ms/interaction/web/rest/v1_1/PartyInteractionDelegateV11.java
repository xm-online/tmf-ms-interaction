package com.icthh.xm.tmf.ms.interaction.web.rest.v1_1;

import com.google.common.collect.ImmutableList;
import com.icthh.xm.commons.lep.LogicExtensionPoint;
import com.icthh.xm.commons.lep.spring.LepService;
import com.icthh.xm.commons.permission.annotation.PrivilegeDescription;
import com.icthh.xm.tmf.ms.interaction.lep.keyresolver.ProfileKeyResolver;
import com.icthh.xm.tmf.ms.interaction.web.api.model.v1_1.PartyInteractionType;
import com.icthh.xm.tmf.ms.interaction.web.api.v1_1.PartyInteractionsV11ApiDelegate;
import io.micrometer.core.annotation.Timed;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@LepService(group = "service")
public class PartyInteractionDelegateV11 implements PartyInteractionsV11ApiDelegate {

    @Timed
    @LogicExtensionPoint(value = "PartyInteractionCreate", resolver = ProfileKeyResolver.class)
    @PreAuthorize("hasPermission({'profile': #profile}, 'INTERACTION.ADD')")
    @Override
    @PrivilegeDescription("Privilege to create a new party interaction")
    public ResponseEntity<PartyInteractionType> createPartyInteraction(PartyInteractionType partyInteractionRequestType,
                                                                       String profile) {
        return ResponseEntity.ok(new PartyInteractionType());

    }

    @Timed
    @LogicExtensionPoint(value = "PartyInteractionRetrieve", resolver = ProfileKeyResolver.class)
    @PreAuthorize("hasPermission({'profile': #profile}, 'INTERACTION.GET')")
    @Override
    @PrivilegeDescription("Privilege to get a party interaction")
    public ResponseEntity<PartyInteractionType> retrievePartyInteraction(String partyInteractionId, String profile) {
        return ResponseEntity.ok(new PartyInteractionType());

    }

    @Timed
    @LogicExtensionPoint(value = "PartyInteractionFind", resolver = ProfileKeyResolver.class)
    @PreAuthorize("hasPermission({'profile': #profile}, 'INTERACTION.GET-LIST')")
    @Override
    @PrivilegeDescription("Privilege to get all the party interactions")
    public ResponseEntity<List<PartyInteractionType>> retrievePartyInteractions(String profile,
                                                                                String accountId,
                                                                                String customerId,
                                                                                String relatedPartyId,
                                                                                OffsetDateTime interactionStartDate,
                                                                                OffsetDateTime interactionEndDate,
                                                                                String channelId,
                                                                                String channelName,
                                                                                String status,
                                                                                String subStatus,
                                                                                String type,
                                                                                String limit,
                                                                                String offset) {
        return ResponseEntity.ok(ImmutableList.of(new PartyInteractionType()));
    }

}
