INSERT INTO public.indicators_queue_configuration (id, high_top_risk_percentage, high_top_risk_procuring_entity_percentage, low_top_risk_percentage, low_top_risk_procuring_entity_percentage, max_high_indicator_impact_range, max_low_indicator_impact_range, max_medium_indicator_impact_range, max_mixed_indicator_impact_range, medium_top_risk_percentage, medium_top_risk_procuring_entity_percentage, min_high_indicator_impact_range, min_low_indicator_impact_range, min_medium_indicator_impact_range, min_mixed_indicator_impact_range, mixed_top_risk_percentage, title) VALUES (1, 10, 5, 20, 10, null, 0.5, 1.2, null, 30, 20, 1.2, 0, 0.5, null, null, 'General queue config') ON CONFLICT DO NOTHING;