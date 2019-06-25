from datetime import datetime, timedelta, time

def calc_today():
    today = datetime.now().date()
    tomorrow = today + timedelta(1)
    today_start = datetime.combine(today, time())
    today_end = datetime.combine(tomorrow, time())
    return (today_start, today_end)