package com.axc.persistence.enums;


/**
 <b>Pre-inquiry:</b> An individual that is in the CRM, but has not taken any marketing activities (i.e. a contact from purchased list with no activity). Frequently these are contacts from data list purchases.
 <p>
 <b>Inquiry:</b> An individual that has taken one or more marketing activities, but has not achieved the lead scoring threshold or route around criteria.
 <p>
 <b>MQL:</b> An individual that’s deemed important enough for inside sales to engage. This is the result of a contact achieving the lead scoring threshold or route around or fast-tracking criteria and as such is viewed to be a marketing qualified lead (MQL). In doing so, the type of MQL (scoring, route around, fast track) should be defined.
 <p>
 <b>Rejected:</b> A MQL that was rejected by inside sales prior to engagement. When a lead is listed as rejected, a conditional field should be used to capture the rejection reason. Reasons for a lead to be rejected include: no contact information, competitor, misrouted, etc. Leads that are rejected should be reviewed by marketing and/or sales operations to improve the organization’s system programs and MQL definitions. When a lead is rejected it is not eligible to become an MQL until it moves to long term nurture.
 <p>
 <b>Engaged:</b> The lead is actively engaged by an inside sales resource, but the rep has not yet had a live conversation or exchange via phone, in-person, or email with the lead.
 <p>
 <b>Connected:</b> The lead is actively engaged by an inside sales resource and the rep has had one or more live conversations or exchanges with the lead.
 <p>
 <b>Disqualified:</b> A lead that inside sales or direct sales engaged or connected with, but which the sales team has determined to be non-viable. When a lead is listed as disqualified, a conditional field should be used to capture the disqualification reason. Disqualification reasons include: unable to reach, no interest, not ready to buy (in the timeframe defined by company), went cold (aka stopped responding), etc. When a lead is disqualified it is not eligible to become an MQL until it moves to long term nurture.
 <p>
 <b>Long term nurture:</b> The lead is primarily being engaged through long-term nurture and there is no active engagement from sales. This pick list value is not available for sales or marketing resources to select. Leads are placed in this status by an automation rule after a specified timeframe after rejection or disqualification (typically six months or half of the average buyer’s journey for the product in question). This status value exists because rejection or disqualification once doesn’t mean a lead is never going to be viable. When a lead achieves this status, it is again eligible to become an MQL.
 <p>
 <b>N/A:</b> This is the lead status value that all individuals from client and partner accounts receive and it indicates that they are not viable candidates for sales to engage. When a contact is placed in this status the status value stays persistent as long as the contact’s company remains a customer. If the company does not renew all contacts with the lead status of N/A, then they move back to the lead status of pre-inquiry (if no activities) or inquiry.
 <p>
 <b>Potential deal:</b> This is what inside sales sends to a direct rep. When a lead is entered into this status we recommend that a zero dollar, no timeline opportunity is created in the CRM to represent the opportunity that is being passed to the direct rep.
 </p>
 <p>
 <b>Pipeline opportunity:</b> This is a potential deal that the direct-rep qualified and is actively working.
 </p>
 */
public enum LeadStatus {
    PRE_INQUIRY,
    INQUIRY,
    MQL,
    REJECTED,
    ENGAGED,
    CONNECTED,
    DISQUALIFIED,
    LONG_TERM_NURTURE,
    N_A,
    POTENTIAL_DEAL,
    PIPELINE_OPPORTUNITY
}
