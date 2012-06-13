package aspects;

public aspect Ordering {
	declare precedence: OutputTracker, DBCPTaint, RequestParameterTaint, GeneralTracker, StringTracking;
}
